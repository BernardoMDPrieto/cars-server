<h1>Garagem virtual - Projeto CRUD</h1>
    <h2>Visão Geral</h2>
    <p>Este projeto é um CRUD básico de carros desenvolvido em Java Spring Boot com gerenciamento de dependências pelo Maven. Ele segue os padrões de arquitetura MVC e possui conexão com um banco de dados MySQL para armazenar e gerenciar informações sobre veículos.</p>
    <h2>Funcionalidades Principais</h2>
    <ul>
        <li>Cadastro, leitura, atualização e exclusão (CRUD) de veículos.</li>
        <li>Validação de dados para garantir integridade e consistência das informações.</li>
    </ul>
    <h2>Tecnologias Utilizadas</h2>
    <ul>
        <li>Java 17</li>
        <li>Spring Boot 3.2.5</li>
        <li>Maven</li>
        <li>MySQL</li>
        <li>JPA/Hibernate para persistência de dados</li>
    </ul>
    <h2>Estrutura do Projeto</h2>
<p>A estrutura do projeto é organizada em camadas para facilitar a organização e manutenção do código:</p>

<ul>
    <li><strong>crud.cars</strong>: Pacote principal do projeto.</li>
    <ul>
        <li><strong>api</strong>: Contém os elementos relacionados à interface de programação.</li>
        <ul>
            <li><strong>request</strong>: Corpo das requisições recebidas do cliente.</li>
            <li><strong>response</strong>: Corpo das respostas enviadas ao cliente.</li>
            <li><strong>resource</strong>: Classes controladoras (Controllers) do sistema.</li>
        </ul>
        <li><strong>exceptions</strong>: Lidando com tratamento de erros da aplicação.</li>
        <li><strong>model</strong>: Contém a entidade principal do banco de dados.</li>
        <li><strong>service</strong>: Regras de negócio e lógica da aplicação.</li>
        <li><strong>repository</strong>: Interfaces para injeção de dependências e acesso ao banco de dados.</li>
    </ul>
</ul>
    <h2>Configuração e Uso</h2>
    <ol>
        <li><strong>Requisitos</strong>:
            <ul>
                <li>JDK 11 ou superior instalado.</li>
                <li>Maven instalado.</li>
                <li>Banco de dados MySQL configurado.</li>
            </ul>
        </li>
        <li><strong>Configuração do Banco de Dados</strong>:
            <ul>
                <li>Crie um banco de dados MySQL chamado <code>cadastro_veiculos</code>.</li>
                <li>Configure as credenciais de acesso ao banco no arquivo <code>application.properties</code>.</li>
            </ul>
        </li>
        <li><strong>Execução do Projeto</strong>:
            <ul>
                <li>Clone o repositório para sua máquina local.</li>
                <li>Navegue até o diretório do projeto e execute o comando <code>mvn spring-boot:run</code> para iniciar o aplicativo.</li>
                <li>Acesse a aplicação pelo navegador através da URL <code>http://localhost:8080</code>.</li>
            </ul>
        </li>
    </ol>
