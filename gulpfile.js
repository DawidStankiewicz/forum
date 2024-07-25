const gulp = require('gulp');
const babel = require("gulp-babel");
const watch = require('gulp-watch');
const browserSync = require('browser-sync').create();
const environments = require('gulp-environments');
const uglifycss = require('gulp-uglifycss');
const terser = require('gulp-terser');
const postcss = require('gulp-postcss');
const purgecss = require('gulp-purgecss');
var sass = require('gulp-dart-sass');


const production = environments.production;

gulp.task('watch', () => {
    browserSync.init(null, {
        port: 5000,
        proxy: '127.0.0.1:8080/',
        open: true
    });

    gulp.watch(['src/main/resources/**/*.html'], gulp.series('copy-html-and-reload'));
    gulp.watch(['src/main/resources/**/*.css', 'src/main/resources/**/*.scss'], gulp.series('copy-scss-and-reload'));
    gulp.watch(['src/main/resources/**/*.js'], gulp.series('copy-js-and-reload'));
});

gulp.task('copy-html', () =>
    gulp.src(['src/main/resources/**/*.html'])
        .pipe(gulp.dest('target/classes/'))
);

gulp.task('copy-scss', () =>
    gulp.src(['src/main/resources/**/*.css', 'src/main/resources/**/*.scss'])
        .pipe(sass().on('error', sass.logError))
        .pipe(postcss([
            require('autoprefixer'),
        ]))
        .pipe(production(purgecss({
            content: ['src/main/resources/templates/**/*.html']
        })))
        .pipe(production(uglifycss()))
        .pipe(gulp.dest('target/classes/'))
);

gulp.task('copy-js', () =>
    gulp.src(['src/main/resources/**/*.js'])
        .pipe(babel())
        .pipe(production(terser()))
        .pipe(gulp.dest('target/classes/'))
);

gulp.task('copy-html-and-reload', gulp.series('copy-html', reload));
gulp.task('copy-scss-and-reload', gulp.series('copy-scss', reload));
gulp.task('copy-js-and-reload', gulp.series('copy-js', reload));

gulp.task('build', gulp.series('copy-html', 'copy-scss', 'copy-js'));
gulp.task('default', gulp.series('watch'));

function reload(done) {
    browserSync.reload();
    done();
}