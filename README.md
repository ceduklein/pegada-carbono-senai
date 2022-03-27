# **PEGADA DE CARBONO**


## **Projeto Integrador SENAI - Escopo**

De acordo com a WWF, fundação que visa mudar a atual trajetória de degradação socioambiental, a pegada de carbono da humanidade é a principal causa das mudanças climáticas. Devido ao fato de que geramos emissões gás carbônico em ritmo muito mais rápido do que é possível absorver, existe um acúmulo de gás carbônico na atmosfera e no oceano. 

Tendo em vista a necessidade de medidas efetivas para redução do impacto ambiental, a empresa Y necessita medir a "pegada de carbono" relativa à emissão de CO2 da sua frota de veículos utilizados por seus colaboradores. Para isso, solicitou o desenvolvimento de um MVP de um sistema que possa realizar este tipo de controle.

A empresa Y é uma organização de suporte de rede que atua em toda a grande Florianópolis desde 2011. Ao todo a empresa possui 107 colaboradores alocados em sua base na região central de Florianópolis. 

A Empresa Y possui 22 colaboradores habilitados a conduzir os veículos de atendimento. Quando um chamado é aberto é verificado o endereço de atendimento e é alocado um carro disponível e colaborador habilitado para a condução do veículo. Ao finalizar o chamado deve ser cadastrada a quilometragem pelo colaborador.


## **Cálculo de CO2 emitido por litro de gasolina**

CG x 0,82 x 0,75 x 3,7 = total de kg CO2 emitido por litro

CG = consumo de gasolina, em litros

0,82 = percentual de gasolina em um litro, descontado o percentual de etanol
(no Brasil a proporção de etanol é de 18% a 25%)

0,75 = densidade da gasolina

3,7 = fator de transformação da gasolina em CO2

Fonte: <https://esalqlastrop.com.br/capa.asp?pi=calculadora_emissoes>


## **Requisitos da aplicação**

1. O sistema deve permitir cadastrar, visualizar, alterar e excluir um funcionário.
2. O sistema deve permitir cadastrar, visualizar, alterar e excluir um veículo.
3. O sistema deve permitir cadastrar, visualizar, alterar e excluir um chamado.
4. O sistema deve calcular e exibir o total de kg de CO2 emitido por chamado.
5. Todo chamado deve possuir um funcionário, um veículo e uma distância percorrida.
6. O sistema deve possuir uma interface gráfica para a interação com o usuário.