package org.wlgzs.manipulation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录id
     */
    @TableId("record_id")
    private Integer recordId;

    /**
     * 会员id
     */
    @TableField("members_id")
    private Integer membersId;

    /**
     * 会员名字
     */
    @TableField("members_name")
    private String membersName;

    /**
     * 记录时间
     */
    @TableField("record_time")
    private LocalDateTime recordTime;

    /**
     * 店员id
     */
    @TableField("staff_id")
    private Integer staffId;

    /**
     * 店员名
     */
    @TableField("staff_name")
    private String staffName;

    /**
     * 推拿种类
     */
    @TableField("tuina_type")
    private String tuinaType;

}
