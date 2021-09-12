package com.beishan.blog.dao;

import com.beishan.blog.bean.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {

    public List<MainMenu> getMenus();
}
