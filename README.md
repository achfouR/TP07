# Perfil do Usuário — FirebaseUI

Projeto Android em **Java + XML**, pacote `br.unisanta.firebase`, vinculado ao arquivo `google-services.json` fornecido.

## Funções
- Login com e-mail e senha usando FirebaseUI.
- Cadastro de novos usuários pelo próprio fluxo da FirebaseUI (e-mail ainda não registrado).
- Tela com e-mail do usuário autenticado e botão **Sair**.
- Sessão preservada ao reabrir o aplicativo; logout retorna ao login.

## Como abrir
1. Extraia o ZIP.
2. No Android Studio, escolha **File > Open** e selecione a pasta `PerfilUsuarioFirebase`.
3. Aguarde a sincronização do Gradle com internet. O projeto usa JDK 17, Android SDK 35, Gradle 8.9 e Android Gradle Plugin 8.7.3.
4. No Firebase Console, em **Authentication > Sign-in method**, ative **E-mail/senha** (não basta ter criado manualmente um usuário).
5. Execute no emulador ou celular Android.
6. Entre com o e-mail e senha que você já cadastrou. Para testar o cadastro, digite um e-mail novo e complete o fluxo FirebaseUI.

## Gradle Wrapper
O ZIP inclui os arquivos de configuração do Gradle, porém **não inclui os binários `gradle-wrapper.jar` nem os scripts executáveis do wrapper**, que não estavam disponíveis no ambiente de geração. Se o Android Studio solicitar o wrapper, crie um projeto Android Studio padrão e copie as pastas `app/` e as configurações Gradle deste projeto para ele, ou gere o wrapper com uma instalação local do Gradle (`gradle wrapper --gradle-version 8.9`).

## Observação
Este projeto contém o arquivo JSON fornecido (identificadores da configuração do Firebase). Não inclui nem precisa incluir as credenciais/senhas dos usuários. Não foi compilado nem executado no Android Studio neste ambiente.
