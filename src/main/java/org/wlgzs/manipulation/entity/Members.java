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
public class Members implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员id 
     */
    @TableId(value = "members_id",type = IdType.AUTO)
    private Integer membersId;

    /**
     * 会员姓名
     */
    private String membersName;

    /**
     * 拼音码
     */
    private String pinyinCode;

    /**
     * 手机号
     */
    private String membersPhone;

    /**
     * 推拿类型id
     */
    private Integer tuinaId;

    /**
     * 推拿类型
     */
    private String tuinaName;

    /**
     * 剩余次数
     */
    private Integer surplusNumber;
}
