schema([cpf, nome, datanasc, senha]).
fds([ [[cpf], [nome, datanasc, senha]] ]).
answer(R3NF) :- schema(R), fds(F), threenf(R,F,R3NF).