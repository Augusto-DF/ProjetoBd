schema([cpf, cpfsup]).
fds([ [[cpf], [cpfsup]] ]).
answer(R3NF):- schema(R), fds(F), threenf(R,F,R3NF).