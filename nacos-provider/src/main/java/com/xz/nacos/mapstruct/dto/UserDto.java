package com.xz.nacos.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * remark.
 *
 * @author xz
 * @date 2023/3/24 14:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String name;

    private String createTime;
}
