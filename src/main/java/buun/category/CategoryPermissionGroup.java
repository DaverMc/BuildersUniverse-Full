package buun.category;

import buun.Permissions;
import buun.user.User;

import java.util.Arrays;
import java.util.List;

public enum CategoryPermissionGroup {

    APPRENTICE(0, Permissions.CATEGORY_GROUP_APPRENTICE),
    CONTENT(1, Permissions.CATEGORY_GROUP_CONTENT),
    BUILDER(2, Permissions.CATEGORY_GROUP_BUILDER);

    private final int index;
    private final String permission;
    CategoryPermissionGroup(int index, String permission){
        this.index = index;
        this.permission = permission;
    }
    public int getIndex() {
        return this.index;
    }

    public static CategoryPermissionGroup getGroup(User user){
        List<CategoryPermissionGroup> filtered = Arrays.stream(values()).filter(permission -> user.hasPermission(permission.permission)).toList();
        return (filtered.isEmpty()) ? null : filtered.get(0);
    }
}
