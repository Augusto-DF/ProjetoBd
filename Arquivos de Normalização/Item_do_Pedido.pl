schema([item, idpedido,  preparado, entregue, idgarcom, detalhes, idestoque, quantidade]).
fds([[[idpedido, item], [preparado, entregue, idgarcom, detalhes, quantidade, idestoque]]]).
answer(R3NF):- schema(R), fds(F), threenf(R,F,R3NF).