<template>
  <section
    id="hero-animated"
    class="hero-animated d-flex align-items-center mt-5"
  >
    <div
      class="container d-flex flex-column justify-content-center align-items-center text-center position-relative"
      data-aos="zoom-out"
    >
      <img src="@/assets/img/main/main.svg" class="img-fluid animated" />
      <h2>Welcome to Enjoy<span>Trip</span></h2>
      <p class="mt-3">
        가고 싶은 여행지를 고르고, 계획을 짜고, 친구들과 공유해보세요
      </p>
      <div class="d-flex mt-3">
        <router-link to="/spot" class="btn-get-started scrollto">
          여행 떠나기
        </router-link>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "MainWelcome",
};
</script>

<style>
.hero-animated {
  width: 100%;
  min-height: 50vh;
  background: url("@/assets/img/main/hero-bg.png") center center;
  background-size: cover;
  position: relative;
  padding: 120px 0 60px;
}
.hero-animated h2 {
  margin: 0 0 10px 0;
  font-size: 48px;
  font-weight: 300;
  color: var(--color-secondary);
  font-family: var(--font-secondary);
}
.hero-animated h2 span {
  color: var(--color-primary);
}
.hero-animated p {
  color: rgba(var(--color-secondary-rgb), 0.8);
  margin: 0 0 30px 0;
  font-size: 20px;
  font-weight: 400;
}
.hero-animated .animated {
  margin-bottom: 60px;
  animation: up-down 2s ease-in-out infinite alternate-reverse both;
}
@media (min-width: 992px) {
  .hero-animated .animated {
    max-width: 45%;
  }
}
@media (max-width: 991px) {
  .hero-animated .animated {
    max-width: 60%;
  }
}
@media (max-width: 575px) {
  .hero-animated .animated {
    max-width: 80%;
  }
}
.hero-animated .btn-get-started {
  font-size: 16px;
  font-weight: 400;
  display: inline-block;
  padding: 10px 28px;
  border-radius: 4px;
  transition: 0.5s;
  color: var(--color-white);
  background: var(--color-primary);
  font-family: var(--font-secondary);
}
.hero-animated .btn-get-started:hover {
  background: rgba(var(--color-primary-rgb), 0.8);
}
.hero-animated .btn-watch-video {
  font-size: 16px;
  transition: 0.5s;
  margin-left: 25px;
  font-family: var(--font-secondary);
  color: var(--color-secondary);
  font-weight: 600;
}
.hero-animated .btn-watch-video i {
  color: var(--color-primary);
  font-size: 32px;
  transition: 0.3s;
  line-height: 0;
  margin-right: 8px;
}
.hero-animated .btn-watch-video:hover {
  color: var(--color-primary);
}
.hero-animated .btn-watch-video:hover i {
  color: rgba(var(--color-primary-rgb), 0.8);
}
@media (max-width: 640px) {
  .hero-animated h2 {
    font-size: 32px;
  }
  .hero-animated p {
    font-size: 18px;
    margin-bottom: 30px;
  }
  .hero-animated .btn-get-started,
  .hero-animated .btn-watch-video {
    font-size: 14px;
  }
}
@-webkit-keyframes up-down {
  0% {
    transform: translateY(10px);
  }
  100% {
    transform: translateY(-10px);
  }
}
@keyframes up-down {
  0% {
    transform: translateY(10px);
  }
  100% {
    transform: translateY(-10px);
  }
}
</style>
