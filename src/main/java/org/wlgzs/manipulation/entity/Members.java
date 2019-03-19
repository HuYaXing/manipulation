package org.wlgzs.manipulation.entity;

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
    @TableId("members_id")
    private Integer membersId;

    /**
     * 会员姓名
     */
    @TableField("members_name")
    private String membersName;

    /**
     * 拼音码
     */
    @TableField("pinyin_code")
    private String pinyinCode;

    /**
     * 手机号
     */
    @TableField("members_phone")
    private String membersPhone;

}
