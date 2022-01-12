update prestamos
set fecha_pago = :fecha_pago,
    valor_prestamo = :valor_prestamo,
    estado_prestamo_pago = :estado_prestamo_pago
    persona = :persona
where id = :id