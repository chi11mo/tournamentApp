package com.chillmo.developer.tournamentApp.user.domain;

public enum Role {
    USER,
    MODERATOR,
    ADMIN;

   // private final Set<UserPermission> permissions;
    /**
    EnumRole() {
        //this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions(){
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions =
                getPermissions().stream()
                        .map(permission -> new SimpleGrantedAuthority((permission.getPermission())))
                        .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority(("ROLE_"+this.name())));
        return permissions;
    }
     **/
}