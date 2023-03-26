package com.atguigu.gulimall.product.entity;

import com.atguigu.common.validator.group.AddGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 品牌分类关联
 * 
 * @author DengPengFei
 * @email DengTPengTFei@163.com
 * @date 2023-02-15 22:32:53
 */
@Data
@TableName("pms_category_brand_relation")
public class CategoryBrandRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 品牌id
	 */
	@NotNull(message = "品牌编号不能为空！", groups = { AddGroup.class })
	private Long brandId;
	/**
	 * 分类id
	 */
	@NotNull(message = "分类编号不能为空！", groups = { AddGroup.class })
	private Long catelogId;
	/**
	 * 
	 */
	private String brandName;
	/**
	 * 
	 */
	private String catelogName;

	@Override
	public String toString() {
		return "CategoryBrandRelationEntity{" +
				"id=" + id +
				", brandId=" + brandId +
				", catelogId=" + catelogId +
				", brandName='" + brandName + '\'' +
				", catelogName='" + catelogName + '\'' +
				'}';
	}
}
