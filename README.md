# Spring Security com Docker

<h2>🛠️ Tecnologias Utilizadas</h2>
<h3>Backend (Java + Spring Boot + JWT + Nimbus JOSE)</h3>
<ul>
    <li><strong>Spring Boot</strong> - Estrutura do servidor</li>
    <li><strong>Spring Security 6</strong> - Implementa autenticação e autorização</li>
    <li><strong>JWT + Nimbus JOSE</strong> - Autenticação via token e senhas RSA</li>
    <li><strong>Spring Data JPA</strong> - Manipulação e persistência de dados</li>
    <li><strong>Postgres Driver</strong> - Conexão com banco de dados PostgreSQL</li>
</ul>

<h3>Autenticação e Autorização com JWT</h3>
<p>Este projeto implementa um sistema de autenticação e autorização com Spring Security 6 utilizando JWT (JSON Web Token). Ele inclui:</p>
<ul>
    <li>Controle de acesso baseado em ROLES (perfis de usuário)</li>
    <li>Assinatura e criptografia de tokens</li>
    <li>Utilização de <strong>annotations de segurança</strong> (@PreAuthorize, etc.)</li>
    <li>Integração com <strong>banco de dados MySQL</strong> para gerenciamento de usuários</li>
</ul>

<h2>🍽️ Recursos Principais</h2>
<ul>
    <li>✅ Criação automática do perfil Admin</li>
    <li>✅ Cadastro de novos usuários BASIC sem necessidade de token</li>
    <li>✅ Listagem de usuários disponível apenas para Admin</li>
    <li>✅ Criação e deleção de tweets (requere token, mas sem restrição de perfil)</li>
    <li>✅ Tweets vinculados ao ID do usuário</li>
    <li>✅ Admin pode deletar qualquer tweet, enquanto usuários normais podem deletar apenas os próprios tweets</li>
</ul>

<h2>🔄 Como Executar o Projeto</h2>

<h3>1. Clonar o Repositório</h3>
<pre><code>git clone https://github.com/Brunosalata/spring-security-docker.git
<br>cd spring-security-docker</code></pre>

<h3>2. Configurar o Backend (Spring Boot)</h3>
<p>Configure o PostgreSQL no <code>application.properties</code>:</p>
<pre><code>spring.datasource.url=jdbc:postgresql://localhost:5432/tweeter
<br>spring.datasource.username=seu_usuario
<br>spring.datasource.password=sua_senha
<br>spring.datasource.drive-class-name=org.postgresql.Driver</code></pre>

Execute o backend:
<pre><code>mvn spring-boot:run</code></pre>
O projeto estará rodando em http://localhost:8080!

<h3>3. Geração de senhas privada e pública</h3>
Utilizando o OpenSSL Command Prompt, gere as chaves em <strong>resources</strong>.
<pre><code>// Criar o par de chaves
openssl genrsa -out PAR_DE_CHAVES.pem
<br>// Extrair chave publica
openssl rsa -in PAR_DE_CHAVES.pem -pubout -out CHAVE_PUBLICA.pem
<br>// Extrair chave privada
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in PAR_DE_CHAVES.pem -out CHAVE_PRIVADA.pem</code></pre>

Incluindo variáveis no <code>application.properties</code>
<pre><code>jwt.private.key=classpath:CHAVE_PRIVADA.pem
jwt.public.key=classpath:CHAVE_PUBLICA.pem</code></pre>

<h2>📊 Modelagem do Banco de Dados</h2>
<p>A estrutura principal do banco de dados inclui:</p>
<ul>
    <li><strong>tb_role</strong> (role_id LONG, role TEXT)</li>
    <li><strong>tb_tweet</strong> (tweet_id LONG, user_id TEXT, content TEXT, creation_timestamp TIMESTAMP)</li>
    <li><strong>tb_user</strong> (user_id TEXT, usename TEXT, password TEXT)</li>
    <li><strong>tb_user_role</strong> (user_id TEXT, role_id LONG)</li>
</ul>

<h2>👨‍💻 Contribuição</h2>
<p>Esse projeto foi desenvolvido para <strong>fins didáticos</strong>, abordando a autenticação e autorização com Spring Security 6 e JWT de forma atualizada. Contribuições são bem-vindas!</p>

<h2>📱 Contato</h2>

**Email:** [brunosalata.dev@gmail.com](mailto:brunosalata.dev@gmail.com)

**LinkedIn:** [brunosalatalima](https://www.linkedin.com/in/brunosalatalima/)

**GitHub:** [Brunosalata](https://github.com/Brunosalata)