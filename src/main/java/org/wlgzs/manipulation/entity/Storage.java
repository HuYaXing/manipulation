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
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 存储id
     */
    @TableId("storage_id")
    private Integer storageId;

    /**
     * 会员id
     */
    @TableField("members_id")
    private Integer membersId;

    /**
     * 推拿种类
     */
    @TableField("tuina_name")
    private String tuinaName;

    /**
     * 剩余次数
     */
    @TableField("surplus_number")
    private Integer surplusNumber;

}
