package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysBuilding;
import com.ylz.system.entity.SysHouse;
import com.ylz.system.mapper.SysBuildingMapper;
import com.ylz.system.service.ISysBuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylz.system.service.ISysHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小区的楼栋 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-05-06
 */
@Service
public class SysBuildingServiceImpl extends ServiceImpl<SysBuildingMapper, SysBuilding> implements ISysBuildingService {
    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private SysBuildingMapper buildingMapper;

    @Autowired
    private ISysHouseService houseService;
    /**
     * 查询所有
     * @return
     */
    @Override
    public List<SysBuilding> getAll() {
        //return buildingMapper.selectPage(null,null);
        return buildingMapper.selectList(null);
    }

    /**
     * 按条件搜索
     * @param param
     * @return
     */
    @Override
    public IPage<SysBuilding> searchBy(Map<String, Object> param) {
        //分页信息
        IPage page = new Page();
        QueryWrapper<SysBuilding> wrapper = new QueryWrapper<>();
        //判断查询条件
        if(param != null){
            if (!StringUtil.isEmpty((String) param.get("startTime"))){
                wrapper.ge("create_time",param.get("startTime"));
            }
            if (!StringUtil.isEmpty((String) param.get("endTime"))){
                wrapper.le("create_time",param.get("endTime"));
            }
            if (!StringUtil.isEmpty((String) param.get("name"))){
                wrapper.like("name",param.get("name"));
            }
            if (!StringUtil.isEmpty((String) param.get("currentPage"))){
                currentPage =  Integer.parseInt((String) param.get("currentPage"));
            }
            if (!StringUtil.isEmpty((String) param.get("pageSize"))){
                pageSize =  Integer.parseInt((String) param.get("pageSize"));
            }
        }
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        //return buildingMapper.selectMapsPage(page,wrapper);
        return buildingMapper.selectPage(page,wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(Map<String,Object> param) {
        int id = (int) param.get("id");
        int resulut = 0;
        try{
            Map<String,Object> deleted = new HashMap<>();
            String houseCode = "";
            if (id > 10) {
                houseCode = String.valueOf(id);

            } else {
                houseCode = "0" +  String.valueOf(id);
            }
            deleted.put("houseCode",houseCode);
            houseService.deleteByHouseCode(deleted);
            resulut=buildingMapper.deleteById(id);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }

        return resulut;
    }

    @Override
    public SysBuilding findById(Integer id) {
        return buildingMapper.selectById(id);
    }

    @Override
    public Integer update(SysBuilding sysBuilding) {
        Integer result=0;
        if (sysBuilding != null){
            QueryWrapper<SysBuilding> wrapper = new QueryWrapper<>();
            wrapper.eq("id",sysBuilding.getId());
            result = buildingMapper.update(sysBuilding,wrapper);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(SysBuilding sysBuilding) {
        int floor = sysBuilding.getTotalLevel();
        int unit = sysBuilding.getTotalUnit();
        int id = sysBuilding.getId();
        int resulut = 0;
        try{
            String buildingName = sysBuilding.getName();
            for (int i=1 ;i<=floor ;i++){
                for (int j=1 ;j<=unit ;j++){
                    for (int k =1 ;k<=2;k++) {
                        SysHouse sysHouse = new SysHouse();
                        sysHouse.setBuildingId(id);
                        sysHouse.setBuildingName(buildingName);
                        sysHouse.setUnit(j);
                        sysHouse.setFloor(i);
                        String x = "";
                        if (id > 10) {
                            x = String.valueOf(id);
                        } else {
                            x = "0" +  String.valueOf(id);
                        }
                        String y = "0" +  String.valueOf(j);

                        String z ="00";
                        if (i > 10) {
                            z = String.valueOf(i);
                        } else {
                            z = "0" + String.valueOf(i);
                        }
                        String m = "0"+String.valueOf(k);
                        sysHouse.setHouseCode(x+y+z+m);
                        sysHouse.setArea("120㎡");
                        sysHouse.setDescription("请修改信息");
                        houseService.add(sysHouse);
                    }
                }
            }
            resulut = buildingMapper.insert(sysBuilding);

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return resulut;
    }
}
