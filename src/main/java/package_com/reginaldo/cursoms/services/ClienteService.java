package package_com.reginaldo.cursoms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import package_com.reginaldo.cursoms.domain.Cliente;
import package_com.reginaldo.cursoms.repositories.ClienteRepository;
import package_com.reginaldo.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		
            Optional<Cliente> obj = repo.findById(id);
            return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
                                            + id + ", Tipo: "
                                            + Cliente.class.getName()));
                
                
                
		
		
		
	}
}
