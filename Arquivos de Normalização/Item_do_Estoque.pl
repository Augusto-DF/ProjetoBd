schema([idestoque, iditem, item, quantidade, valor]).
fds([ [[idestoque, iditem], [item, quantidade, valor]] ]).
answer(R3NF):- schema(R), fds(F), threenf(R,F,R3NF).