create table abonos (
 id int(11) not null auto_increment,
 fecha_abono datetime not null,
 valor_abono int not null,
 prestamo int not null,
 primary key (id),
 FOREIGN KEY (prestamo)
         REFERENCES prestamos(id)
);