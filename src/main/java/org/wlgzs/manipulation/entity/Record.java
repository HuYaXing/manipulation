package org.wlgzs.manipulation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
    @TableId(value = "record_id",type = IdType.AUTO)
    private Integer recordId;

    /**
     * 会员id
     */
    private Integer membersId;

    /**
     * 会员名字
     */
    private String membersName;

    /**
     * 记录时间
     */
    private LocalDateTime recordTime;

    /**
     * 店员id
     */
    private Integer staffId;

    /**
     * 店员名
     */
    private String staffName;

    /**
     * 推拿种类
     */
    private String tuinaType;

}
