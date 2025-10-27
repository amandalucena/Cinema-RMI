# Como rodar o servidor e o cliente RMI localmente (Windows PowerShell)

Este guia mostra passos mínimos para executar o servidor RMI e o cliente na mesma máquina (ou em máquinas distintas na mesma rede local).

Pré-requisitos
- Java JDK instalado e disponível no PATH
- Ant instalado (opcional, o projeto tem `build.xml`)

Passos rápidos (diretório do workspace: `c:\CinemaRMI\Cinema-RMI`)

1) Compilar (usando Ant — opcional)

No PowerShell:

```powershell
cd 'c:\CinemaRMI\Cinema-RMI\Servidor'
ant
# Observação: se preferir compilar manualmente, use javac apontando para src e destino em build/classes
```

2) Rodar o servidor (build/classes já contém as classes compiladas)

```powershell
cd 'c:\CinemaRMI\Cinema-RMI\Servidor'
# Executa a classe main do servidor
java -cp build/classes servidor.main.ServidorMain
```

- O servidor, por código, tentará definir `java.rmi.server.hostname` automaticamente para o IP local.
- No console você deverá ver logs como:
  - `java.rmi.server.hostname definido para 192.168.x.x` (ou similar)
  - `RMI Registry iniciado na porta 1099.`
  - `Servidor RMI no ar!`

3) Rodar o cliente (na mesma máquina)

```powershell
cd 'c:\CinemaRMI\Cinema-RMI\Cliente'
# Se quiser conectar ao servidor local (localhost):
java -cp build/classes client.Client
# Ou forneça o IP do servidor (ex.: 192.168.0.10):
java -cp build/classes client.Client 192.168.0.10
```

- O cliente agora aceita o host do servidor como primeiro argumento. Ele tentará conectar em `rmi://<host>/CinemaService`.

Notas e resolução de problemas
- Se o cliente estiver em outra máquina, substitua `localhost` pelo IP do servidor na linha de comando.
- Firewall: verifique se a porta TCP 1099 está aberta entre as máquinas (ou desative temporariamente o firewall para teste).
- Se ocorrer `ConnectException: Connection refused`, verifique se o servidor está rodando e se o `java -cp ... servidor.main.ServidorMain` foi executado com sucesso.
- Se houver `UnknownHostException`, confirme o IP/hostname fornecido ao cliente.
- Para debugging, cole aqui o stacktrace completo que aparece no console do cliente e o log do servidor — eu analiso.

Alternativa: rodar com propriedade de sistema explícita

Se preferir não depender da detecção automática, inicie o servidor com a propriedade:

```powershell
java -Djava.rmi.server.hostname=192.168.0.10 -cp build/classes servidor.main.ServidorMain
```

Isto força o hostname embutido nos stubs RMI.

Se quiser que eu fixe também a porta de export do stub (para evitar portas dinâmicas) ou gere um jar executável para o servidor/cliente, posso fazer isso em seguida.
