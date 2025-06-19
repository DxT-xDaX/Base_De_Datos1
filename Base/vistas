show full tables;

create view vista_cliente_procurador as
select distinct
		a.nombre as Cliente,
		b.id_cso as Caso,
		d.nombre as Procurador
		
from cliente a, expediente b, expediente_procurador c, procurador d
where a.id_cte=b.id_cte and b.id_cso=c.id_cso and c.id_pcr=d.id_pcr;

select * from vista_cliente_procurador;

create view vista_abogado_expediente as
select distinct
		 a.nombre as abogado,
		 a.telefono,
		 b.situacion,
		 c.id_cso as Caso
		 
from abogado a 
inner join expediente_abogado b on a.id_abo=b.id_abo
inner join expediente c 		on b.id_cso=c.id_cso
where b.situacion='Vigente';

select * from vista_abogado_expediente;
