package com.wzp.user.benmon.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-19 15:42:15
 */
@Data
@TableName("station")
@NoArgsConstructor
public class StationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 *
	 */
	private String  locos;
	/**
	 *
	 */
	private Integer count;
	/**
	 *
	 */


	public StationEntity(Long id, String locos, Integer count) {
		this.id = id;
		this.locos = locos;
		this.count = count;
	}
}
