package  com.wzp.ttt.mon.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
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
@TableName("user_info")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 *
	 */
	private String namw;
	/**
	 *
	 */
	private Integer age;
	/**
	 *
	 */
	private String location;

    @TableField(exist = false)
	private StationEntity stationEntity;
	public UserInfoEntity(String namw, Integer age, String location) {
		this.namw = namw;
		this.age = age;
		this.location = location;
	}
}
