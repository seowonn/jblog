package jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	private SqlSession sqlSession;
	
	public UserRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public boolean findById(String id) {
		return sqlSession.selectOne("user.existsById", id);
	}

	public void addUser(UserVo userVo) {
		sqlSession.insert("user.insert", userVo);
	}
	
	public UserVo findByIdAndPassword(String id, String password) {		
		return sqlSession.selectOne("user.findByIdAndPassword", Map.of("id", id, "password", password));		
	}

}
