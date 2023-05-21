# BungeeChannels
Плагин, демонстрирующий работу с каналами BungeeCord. Устанавливается в качестве плагина и на Bukkit, и на BungeeCord.

BungeeChannels - основной класс. При включении происходит регистрация канала, слушателя и тестовой команды.

CommandHandler - обработчик тестовой команды. В нём происходит создание массива байтов (с предварительной подготовкой к отправке, т.е. запись заголовков), запись нужных вам данных и отправка через некое такое API в BungeeSpigot.

BungeeBukkit - реализует регистрацию необходимых компонентов, отправку и получение сообщений BungeeCord. onPluginMessageReceived - обработчик полученных сообщений. Сначала проверяет принадлежность сообщения к этому плагину, далее выполняет действия с вашими данными.

BungeeBungee - переадресатор, позволяет BungeeCord получить сообщение от одного сервера и разослать его остальным. В нём менять ничего не нужно.
