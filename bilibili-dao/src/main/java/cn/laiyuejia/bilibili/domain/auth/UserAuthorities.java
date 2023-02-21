package cn.laiyuejia.bilibili.domain.auth;

import lombok.Data;

import java.util.List;

@Data
public class UserAuthorities {

    List<AuthRoleElementOperation> roleElementOperationList;

    List<AuthRoleMenu> roleMenuList;

}
