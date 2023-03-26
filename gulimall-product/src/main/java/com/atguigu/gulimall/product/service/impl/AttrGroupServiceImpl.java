package com.atguigu.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.AttrGroupDao;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 可以根据商品分类编号查询
     * 可以对属性分组编号和属性分组名称进行模糊查询
     * @param params 通用分页查询参数
     * @param catelogId 商品分类编号
     * @return 分页数据
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        if (catelogId != 0) {
            queryWrapper.eq("catelog_id", catelogId);
        }
        String key = (String) params.get("key");
        if (catelogId != 0 && StringUtils.isNotBlank(key)) {
            queryWrapper.and((obj)-> {
                obj.like("attr_group_id", key);
                obj.or().like("attr_group_name", key);
            });
        }
        if (catelogId == 0 && StringUtils.isNotBlank(key)) {
            queryWrapper.like("attr_group_id", key);
            queryWrapper.or().like("attr_group_name", key);
        }
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }



}