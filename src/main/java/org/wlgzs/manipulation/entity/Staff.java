package org.wlgzs.manipulation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 胡亚星
 * @since 2019-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工id
     */
    @TableId(value = "staff_id",type = IdType.AUTO)
    private Integer staffId;

    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 员工手机号
     */
    private String staffPhone;

    /**
     * 员工密码
     */
    private String staffPassword;

    /**
     * 员工等级（0是老板，1是员工）
     */
    private Integer staffLevel;
}
