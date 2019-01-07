package package_com.reginaldo.cursoms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import package_com.reginaldo.cursoms.domain.Pedido;
import package_com.reginaldo.cursoms.repositories.PedidoRepository;
import package_com.reginaldo.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		
            Optional<Pedido> obj = repo.findById(id);
            return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
                                            + id + ", Tipo: "
                                            + Pedido.class.getName()));
                
                
                
		
		
		
	}
}
