schema([idpedido, cpfcliente, forma_pagame, fechado]).
fds([ [[idpedido], [cpfcliente, forma_pagame, fechado]] ]).
answer(R3NF):- schema(R), fds(F), threenf(R,F,R3NF).