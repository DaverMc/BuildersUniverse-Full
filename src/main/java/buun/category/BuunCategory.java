package buun.category;

import buun.user.User;

import static buun.category.CategoryPermissionState.*;

public class BuunCategory implements Category{

    private final String prefix;

    private final String name;

    private final CategoryPermissionState[] permissionStates;

    private int worldCount;

    public BuunCategory(String prefix, String name){
        this.prefix = prefix;
        this.name = name;
        this.worldCount = 0;
        this.permissionStates = new CategoryPermissionState[CategoryPermissionGroup.values().length];
        setPermissions(HIDDEN);
    }

    public BuunCategory(String prefix, String name, CategoryPermissionState[] states){
        this.prefix = prefix;
        this.name = name;
        this.permissionStates = states;
        this.worldCount = 0;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getWorldCount() {
        return this.worldCount;
    }

    @Override
    public void increaseWorldCount() {
        this.worldCount++;
    }

    @Override
    public boolean canView(User user) {
        CategoryPermissionGroup group = CategoryPermissionGroup.getGroup(user);
        if(group == null) return false;
        return permissionStates[group.getIndex()] != HIDDEN;
    }

    @Override
    public boolean canBuild(User user) {
        CategoryPermissionGroup group = CategoryPermissionGroup.getGroup(user);
        if(group == null) return false;
        return permissionStates[group.getIndex()] == EDITABLE;
    }

    @Override
    public void setPermission(CategoryPermissionGroup group, CategoryPermissionState state) {
        this.permissionStates[group.getIndex()] = state;
    }

    @Override
    public CategoryPermissionState[] getPermissions() {
        return this.permissionStates;
    }

    @Override
    public Category setWorldCount(int worldCount) {
        this.worldCount = worldCount;
        return this;
    }
}
