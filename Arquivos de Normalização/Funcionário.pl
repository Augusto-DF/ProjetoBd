schema([cpf]).
fds([]).
answer(R3NF):- schema(R), fds(F), threenf(R,F,R3NF).