package com.wzf.user;

import javax.mail.MessagingException;

import org.springframework.transaction.annotation.Transactional;

import com.wzf.utils.MailUtils;
import com.wzf.utils.UUIDUtils;

@Transactional
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void register(User user) {
		// TODO Auto-generated method stub
		user.setState(0);			//0±íÊ¾Î´¼¤»î
		String code=UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		
		try {
			MailUtils.sendMail(user.getEmail(), code);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return userDao.findByCode(code);
	}

	public void update(User userFromTable) {
		// TODO Auto-generated method stub
		userDao.update(userFromTable);
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}
	
}
