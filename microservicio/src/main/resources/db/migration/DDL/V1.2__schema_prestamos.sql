create table prestamos (
 id int(11) not null auto_increment,
 fecha_pago datetime not null,
 valor_prestamo int not null,
 estado_prestamo_pago boolean DEFAULT false,
 persona int not null,
 primary key (id),
 FOREIGN KEY (persona)
         REFERENCES persona(id)
);