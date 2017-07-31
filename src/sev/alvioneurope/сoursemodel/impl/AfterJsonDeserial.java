package sev.alvioneurope.сoursemodel.impl;

//Интерфейс механизма "Донастройка после JSON"
interface AfterJsonDeserial {
	default void _afterJsonDeserial() {};
}
