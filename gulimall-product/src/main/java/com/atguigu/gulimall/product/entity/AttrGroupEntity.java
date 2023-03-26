package com.atguigu.gulimall.product.entity;

import com.atguigu.common.validator.group.AddGroup;
import com.atguigu.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 属性分组
 * 
 * @author DengPengFei
 * @email DengTPengTFei@163.com
 * @date 2023-02-15 22:32:53
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分组id
	 */
	@TableId
	@Null(message = "属性分组编号不能提交！", groups = { AddGroup.class })
	@NotNull(message = "属性分组编号不能为空！", groups = { UpdateGroup.class })
	private Long attrGroupId;
	/**
	 * 组名
	 */
	@NotNull(message = "属性分组名称不能为空！", groups = { AddGroup.class, UpdateGroup.class })
	private String attrGroupName;
	/**
	 * 排序
	 */
	@NotNull(message = "属性分组的排序不能为空！", groups = { AddGroup.class })
	private Integer sort;
	/**
	 * 描述
	 */
	private String descript;
	/**
	 * 组图标
	 */
	@NotNull(message = "属性分组图标不能为空！", groups = { AddGroup.class })
	private String icon;
	/**
	 * 所属分类id
	 */
	@NotNull(message = "属性分组的分类不能为空！", groups = { AddGroup.class })
	private Long catelogId;

	@TableField(exist = false)
	private List<Long> catelogPath;

}
