Feature: Gerador de Eventos
Realizar o envio de eventos para o protegemed

Scenario: Preencher os elementos da pagina e realizar o submit - UC01
Given Abrir o browser Chrome
When Abrir a Home do Protegemed
Then Verificar que os elementos de input estao presentes
And preencher os elementos com valores
And clicar no botao enviar

Scenario: Preencher os elementos da pagina e realizar o submit - UC02
Given Abrir o browser Chrome
When Abrir a Home do Protegemed
Then Verificar que os elementos de input estao presentes
And preencher os elementos com valores
And clicar no botao enviar