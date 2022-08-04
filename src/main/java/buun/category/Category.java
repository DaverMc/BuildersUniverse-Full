package buun.category;

import buun.user.User;

public interface Category {

    String getPrefix();

    String getName();

    int getWorldCount();

    void increaseWorldCount();

    boolean canView(User user);

    boolean canBuild(User user);

    void setPermission(CategoryPermissionGroup group, CategoryPermissionState state);

    CategoryPermissionState[] getPermissions();

    Category setWorldCount(int worldCount);

    default Category setPermissions(CategoryPermissionState stateAll){
        for(int i = 0; i < CategoryPermissionGroup.values().length; i++) getPermissions()[i] = stateAll;
        return this;
    }

}
