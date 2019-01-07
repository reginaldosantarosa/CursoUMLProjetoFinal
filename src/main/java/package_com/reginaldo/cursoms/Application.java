package package_com.reginaldo.cursoms;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import package_com.reginaldo.cursoms.domain.Categoria;
import package_com.reginaldo.cursoms.domain.Cidade;
import package_com.reginaldo.cursoms.domain.Cliente;
import package_com.reginaldo.cursoms.domain.Endereco;
import package_com.reginaldo.cursoms.domain.Estado;
import package_com.reginaldo.cursoms.domain.ItemPedido;
import package_com.reginaldo.cursoms.domain.Pagamento;
import package_com.reginaldo.cursoms.domain.PagamentoComBoleto;
import package_com.reginaldo.cursoms.domain.PagamentoComCartao;
import package_com.reginaldo.cursoms.domain.Pedido;
import package_com.reginaldo.cursoms.domain.Produto;
import package_com.reginaldo.cursoms.domain.enums.EstadoPagamento;
import package_com.reginaldo.cursoms.domain.enums.TipoCliente;

import package_com.reginaldo.cursoms.repositories.CategoriaRepository;
import package_com.reginaldo.cursoms.repositories.CidadeRepository;
import package_com.reginaldo.cursoms.repositories.ClienteRepository;
import package_com.reginaldo.cursoms.repositories.EnderecoRepository;
import package_com.reginaldo.cursoms.repositories.EstadoRepository;
import package_com.reginaldo.cursoms.repositories.ItemPedidoRepository;
import package_com.reginaldo.cursoms.repositories.PagamentoRepository;
import package_com.reginaldo.cursoms.repositories.PedidoRepository;
import package_com.reginaldo.cursoms.repositories.ProdutoRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private EstadoRepository estadoRepository;
    
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
        
        Cliente cli1= new Cliente(null, "Maria Silva", "email@gmail.com", "30303030", TipoCliente.PESSOA_FISICA);        
        cli1.getTelefones().addAll(Arrays.asList("99977256","32323232"));                
        Endereco e1 = new Endereco(null, "rua Flores", "300", "apap303", "Jardins", "8184320", cli1, c3);
        Endereco e2 = new Endereco(null, "rua matos", "30", "-", "Saci", "818320", cli1, c2);        
        cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
        
        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1,e2));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
        

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));	
    }
}
