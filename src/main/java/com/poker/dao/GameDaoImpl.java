package com.poker.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.poker.dao.AbstractBaseDaoImpl;
import com.poker.dao.PlayerDao;
import com.poker.Game;

public class GameDaoImpl extends AbstractBaseDaoImpl<Game> implements GameDao {

}