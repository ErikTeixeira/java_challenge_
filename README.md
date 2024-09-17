Como Abrir e Executar o Projeto no Azure App Service
Pré-requisitos:
Acesso à conta do Azure.
Java JDK 8 ou superior instalado.
Maven instalado.
O projeto já deve estar configurado no Azure App Service.

1. Clone o Repositório Localmente
Antes de tudo, é preciso ter o código do projeto em sua máquina local. Para isso:
Abra o terminal ou o prompt de comando.
Clone o repositório usando o seguinte comando:

git clone https://github.com/seu-usuario/seu-repositorio.git

2. Compile e Empacote a Aplicação
No diretório raiz do projeto, execute o Maven para compilar e empacotar a aplicação:

mvn clean package

Este comando criará o arquivo JAR na pasta target.

3. Execute o Projeto Localmente (Opcional)
Antes de fazer o deploy no Azure, teste o projeto localmente. Use o seguinte comando:

java -jar target/sua-aplicacao.jar

Isso iniciará o servidor localmente, e você pode acessar a aplicação em http://localhost:8080.

Passo a Passo no Azure
Agora, para abrir o projeto no Azure App Service, siga as instruções abaixo:

4. Acesse o Portal do Azure
Login no Portal do Azure.
Navegue até App Services.
Na lista, clique no nome do App Service onde seu projeto foi implantado.

5. Verifique o Status do Aplicativo
Na página do App Service, você verá o status da sua aplicação. Certifique-se de que o status está como Running.

6. Abra o Fluxo de Logs (Log Stream)
Na barra lateral esquerda, clique em Log Stream.

O log de diagnóstico será exibido, mostrando informações em tempo real sobre o funcionamento do aplicativo.

Duplicar Tela: Agora, abra uma nova aba do navegador para manter a visualização dos logs ativa.

7. Acesse a Aplicação em Execução
No App Service, clique em URL (geralmente algo como https://sprint3-devops.azurewebsites.net).
A aplicação será aberta em uma nova aba. Isso permite visualizar a execução em tempo real enquanto monitora os logs na outra aba.
8. Monitoramento e Diagnóstico
Enquanto sua aplicação está em execução, você pode monitorar o desempenho e identificar problemas através dos logs.
Caso o aplicativo não funcione corretamente, volte para o Log Stream e veja os erros relatados

caso sejá necessário, login e senha do azure com a aplicação:
rm551456@fiap.com.br
Gui22954611!