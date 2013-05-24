package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Font;
import br.com.gp.inventory.domain.repository.FontRepository;
import br.com.gp.inventory.domain.service.FontService;

@Component("fontService")
@Interceptors(value = {ServiceInteceptor.class})
public class FontServiceImpl implements FontService {


	@Autowired
	@Qualifier(value = "fontRepository")
	private FontRepository repository;

	@Override
	public void save(Font font) throws ServiceException {
		this.repository.save(font);
	}

	@Override
	public List<Font> findAll() throws ServiceException {
		return (List<Font>) this.repository.findAll();
	}
}
