package daw.cookcinando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daw.cookcinando.model.User;
import daw.cookcinando.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findOne(long id) {
		return userRepository.findOne(id);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public void saveAndFlush(User user) {
		userRepository.saveAndFlush(user);
	}
	
	public void delete(long id) {
		userRepository.delete(id);
	}
	
	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
		/*User user = null;
		
		for(User u : userRepository.findAll()){
			if(u.getEmail() == email){
				user = u;
				break;
			}
		}
		return user;*/
	}
}
