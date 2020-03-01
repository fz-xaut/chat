package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import entity.user;
import mapper.userMapper;

public class searchDao {
	
	//≤È—Øµ«¬º
	public static String getUserNameAndPassword(user user) {
		SqlSession sqlSession = sqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		userMapper userMapper = sqlSession.getMapper(userMapper.class);
		System.out.println(user.getUsername());
		user user1 = userMapper.getUserAndPassword(user.getUsername());
		sqlSession.close();
		if (user1 == null) {
			return "not";
		}else {
			if (user1.getPassword().equals(user.getPassword())) {
				return "success";
			}else {
				return "error";
			}
		}
	}
	
	public static String SearchUserName(user user) {
		
		String result = getUserNameAndPassword(user);
		if (result.equals("not"))
			return "user not exist";
		if (result.equals("error"))
			return "password error";
		return "success";
	}
	//≤È—Ø∫√”—
	public static String getContacts(String username) {
		SqlSession sqlSession = sqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		userMapper userMapper = sqlSession.getMapper(userMapper.class);
		String contacts = userMapper.getContacts(username);
		sqlSession.close();
		return contacts;
	}
}
