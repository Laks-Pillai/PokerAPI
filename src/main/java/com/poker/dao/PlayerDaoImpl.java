package com.poker.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import com.poker.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.poker.dao.AbstractBaseDaoImpl;
import com.poker.dao.PlayerDao;


public class PlayerDaoImpl extends AbstractBaseDaoImpl<Player> implements PlayerDao {

}