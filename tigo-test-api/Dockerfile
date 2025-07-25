# Imagen base
FROM php:8.2-apache

# Variables para Laravel
ENV APACHE_DOCUMENT_ROOT=/var/www/html/public

# Establece el directorio de trabajo
WORKDIR /var/www/html

# Instala dependencias necesarias
RUN apt-get update && apt-get install -y \
    unzip \
    libpng-dev \
    libonig-dev \
    libxml2-dev \
    zip \
    curl \
    nano \
    netcat-openbsd \
    && docker-php-ext-install pdo pdo_mysql mbstring exif pcntl bcmath gd

# Habilita Rewrite para Laravel
RUN a2enmod rewrite \
 && sed -ri 's!/var/www/html!${APACHE_DOCUMENT_ROOT}!g' /etc/apache2/sites-available/000-default.conf

# Copia el contenido del proyecto Laravel
COPY . .

# Genera la clave de la app
RUN php artisan key:generate

# Prepara el entorno de sesiones
RUN mkdir -p storage/framework/sessions \
 && mkdir -p storage/logs \
 && mkdir -p bootstrap/cache \
 && chown -R www-data:www-data storage bootstrap/cache \
 && chmod -R 775 storage bootstrap/cache

# Instala Composer sin dev
RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer \
 && composer install --no-dev --optimize-autoloader

# Exponer el puerto 80
EXPOSE 80

# Comando final
CMD ["apache2-foreground"]