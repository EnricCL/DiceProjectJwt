package com.ecomesbe.dicejwt.service.impl;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecomesbe.dicejwt.dao.IPlayerDAO;
import com.ecomesbe.dicejwt.dto.Player;

@Service
public class PlayerDetailsServiceImpl implements UserDetailsService {

	private IPlayerDAO iPlayerDAO;
	
	public PlayerDetailsServiceImpl(IPlayerDAO iPlayerDAO) {
		this.iPlayerDAO = iPlayerDAO;
	}
	
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Player player = iPlayerDAO.findPlayerByName(name);
		if(player==null) {
			throw new UsernameNotFoundException(name);
		}
		return new User(player.getName(), player.getPassword(), emptyList());
	}
}
