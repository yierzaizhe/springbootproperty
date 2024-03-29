package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysParking;
import com.ylz.system.entity.SysParkingUse;
import com.ylz.system.entity.SysStock;
import com.ylz.system.mapper.SysParkingUseMapper;
import com.ylz.system.mapper.SysStockMapper;
import com.ylz.system.service.ISysStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 小区后勤的库存 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
@Service
public class SysStockServiceImpl extends ServiceImpl<SysStockMapper, SysStock> implements ISysStockService {

    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private SysStockMapper stockMapper;

    @Override
    public IPage<SysStock> searchBy(Map<String, Object> param) {
        //分页信息
        IPage page = new Page();
        QueryWrapper<SysStock> wrapper = new QueryWrapper<>();
        //判断查询条件
        if(param != null){
            if (!StringUtil.isEmpty((String) param.get("goods"))){
                wrapper.like("goods",param.get("goods"));
            }

            if (!StringUtil.isEmpty((String) param.get("startTime"))){
                wrapper.ge("create_time",param.get("startTime"));
            }
            if (!StringUtil.isEmpty((String) param.get("endTime"))){
                wrapper.le("create_time",param.get("endTime"));
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
        return stockMapper.selectPage(page,wrapper);
    }

    @Override
    public int count(Map<String, Object> param) {
        return 0;
    }

    @Override
    public Integer delete(Map<String, Object> param) {
        int id = (int) param.get("id");
        return stockMapper.deleteById(id);
    }

    @Override
    public SysParking findById(Integer id) {
        return null;
    }

    @Override
    public Integer update(SysStock stock) {
        Integer result=0;
        if (stock != null){
            QueryWrapper<SysStock> wrapper = new QueryWrapper<>();
            wrapper.eq("id",stock.getId());
            result = stockMapper.update(stock,wrapper);
        }
        return result;
    }

    @Override
    public Integer add(SysStock stock)  {
        return stockMapper.insert(stock);
    }
}
