# Aplicativo de comunicação em chat utilizando sockets e therads

***Disciplina:*** Arquitetura de Redes de Computador <br/>
**Docente:** Luciano Cavalheiro <br/>
**Discente:** Ryan Paulo | Isabelle Cedro | Leonardo Cordeiro | Luana Bezerra | Kaique Alves <br/>

### Chat em Rede Local em Java
Este projeto implementa um chat em rede local utilizando sockets, threads e um banco de dados. Os usuários podem fazer login, e se não tiverem cadastro, podem ser redirecionados para a tela de cadastro para efetuar o cadastro. Após o login, eles podem abrir a tela do chat e aguardar a conexão de outros clientes. A conexão é feita por multithreading, permitindo que vários usuários se conectem ao mesmo tempo e troquem mensagens.<br/><br/>

### Ferramentas Utilizadas
**NetBeans:** Utilizado para o desenvolvimento da interface gráfica do chat.<br/>
**Adobe Photoshop:** Utilizado para criar e editar elementos visuais da interface, como ícones e botões.<br/>
**Visual Studio Code** Utilizado para o restante do desenvolvimento do projeto, incluindo a lógica do chat, manipulação de sockets e gerenciamento de threads.<br/>
**MySQL Workbench:** Utilizado para criar a tabela no banco de dados e gerenciar as informações dos usuários.<br/><br/>


### Funcionalidades
**Login e Cadastro:** Os usuários podem fazer login com suas credenciais ou criar uma nova conta.<br/>
**Chat Multithread:** O servidor gerencia várias conexões simultâneas, permitindo que os clientes troquem mensagens em tempo real.<br/>
**Banco de Dados:** As informações dos usuários são armazenadas em um banco de dados para autenticação e gerenciamento.<br/><br/>

### Como Executar o Projeto
**1. Configuração do Banco de Dados:** Crie um banco de dados chamado "chat" no MySQL. O slq esta disponivel na pasta, identificado como **"TableLogin.sql"**. <br/>
**2. Execução dos Servidores:** <br/>
**-** Execute o projeto ServidorBD para iniciar o servidor de banco de dados (porta 4444).<br/>
**-** Execute o projeto Server para iniciar o servidor de chat (porta 3333).<br/>
**3. Execute a Aplicação:** Execute o projeto LoginView para iniciar o cliente de chat.<br/><br/>

### Observações
O gerenciador de banco de dados usado no projeto foi o "MySql Workbench". Caso opite por outro, configure de acordo com os parametros necessarios para o que opitou.<br/>
Certifique-se de a configuração do banco de dados foi feita corretamente.<br/>
Motifique os parametros de acesso ao banco de dados (MySql) de acordo com o "login" e "senha" que você definiu seu gerenciador de banco de dados.<br/>


