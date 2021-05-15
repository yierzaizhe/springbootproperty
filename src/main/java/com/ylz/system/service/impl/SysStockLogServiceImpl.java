package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysCommunity;
import com.ylz.system.entity.SysParking;
import com.ylz.system.entity.SysStock;
import com.ylz.system.entity.SysStockLog;
import com.ylz.system.mapper.SysStockLogMapper;
import com.ylz.system.mapper.SysStockMapper;
import com.ylz.system.service.ISysStockLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 小区后勤的库存采购日志 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-05-15
 */
@Service
public class SysStockLogServiceImpl extends ServiceImpl<SysStockLogMapper, SysStockLog> implements ISysStockLogService {
    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private SysStockLogMapper stockMapper;
    @Override
    public IPage<SysStockLog> searchBy(Map<String, Object> param) {
        //分页信息
        IPage page = new Page();
        QueryWrapper<SysStockLog> wrapper = new QueryWrapper<>();
        //判断查询条件
        if(param != null){
            if (!StringUtil.isEmpty((String) param.get("goods"))){
                wrapper.like("goods",param.get("goods"));
            }
            if (!StringUtil.isEmpty((String) param.get("total"))){
                wrapper.like("total",param.get("total"));
            }
            if (!StringUtil.isEmpty((String) param.get("isExamine"))){
                wrapper.like("is_examine",param.get("isExamine"));
            }
            if (!StringUtil.isEmpty((String) param.get("username"))){
                wrapper.like("username",param.get("username"));
            }
            if (!StringUtil.isEmpty((String) param.get("logRemark"))){
                wrapper.like("log_remark",param.get("logRemark"));
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
    public Integer update(SysStockLog stock) {
        Integer result=0;
        if (stock != null){
            QueryWrapper<SysStockLog> wrapper = new QueryWrapper<>();
            wrapper.eq("id",stock.getId());
            result = stockMapper.update(stock,wrapper);
        }
        return result;
    }

    @Override
    public Integer add(SysStockLog stock) {
        return stockMapper.insert(stock);
    }

    @Override
    public Boolean updateStatus(String status, Integer id) {
        SysStockLog stockLog = new SysStockLog();
        stockLog.setIsExamine(status);
        QueryWrapper<SysStockLog> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        int row = stockMapper.update(stockLog,wrapper);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }
}
