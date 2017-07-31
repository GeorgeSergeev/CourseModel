package sev.alvioneurope.coursemodel.impl;

//Интерфейс механизма "Донастройка после JSON"
interface AfterJsonDeserial {
	default void _afterJsonDeserial() {};
}
