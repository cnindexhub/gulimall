package com.atguigu.gulimall.product.entity;

import com.atguigu.common.validator.ListValue;
import com.atguigu.common.validator.UpdateStatusGroup;
import com.atguigu.common.validator.group.AddGroup;
import com.atguigu.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 * 
 * @author DengPengFei
 * @email DengTPengTFei@163.com
 * @date 2023-02-15 22:32:53
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@NotNull(message = "品牌编号不能为空！", groups = { UpdateGroup.class })
	@Null(message = "品牌编号不允许提交！", groups = { AddGroup.class })
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名称不能为空！", groups = { AddGroup.class, UpdateGroup.class })
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank(message = "品牌logo地址不能为空", groups = { AddGroup.class })
	@URL(message = "品牌logo地址格式不正确", groups = { AddGroup.class, UpdateGroup.class })
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@NotNull(message = "显示状态不能为空", groups = { AddGroup.class, UpdateStatusGroup.class })
	@ListValue(values = {0 , 1}, groups = { AddGroup.class, UpdateStatusGroup.class}, message = "必须提交指定的值")
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotBlank(message = "检索首字母不能为空", groups = { AddGroup.class })
	@Pattern(regexp = "^[a-zA-Z]$", message = "只能提交单个字母", groups = { AddGroup.class, UpdateGroup.class })
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(message = "排序不能为空", groups = { AddGroup.class })
	@Min(value = 0, message = "排序必须大于等于0", groups = { AddGroup.class, UpdateGroup.class })
	private Integer sort;

	@Override
	public String toString() {
		return "BrandEntity{" +
				"brandId=" + brandId +
				", name='" + name + '\'' +
				", logo='" + logo + '\'' +
				", descript='" + descript + '\'' +
				", showStatus=" + showStatus +
				", firstLetter='" + firstLetter + '\'' +
				", sort=" + sort +
				'}';
	}
}
